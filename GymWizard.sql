-- ==========================
-- RESET COMPLETO
-- ==========================

DROP DATABASE IF EXISTS PalestraDB;
CREATE DATABASE PalestraDB;
USE PalestraDB;

-- ==========================
-- TABELLA CREDENZIALI
-- ==========================
CREATE TABLE Credenziali (
    email VARCHAR(50) PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL,
    ruolo INT NOT NULL -- 1 = CLIENTE, 2 = PERSONAL_TRAINER, 3 = ADMIN
);

-- ==========================
-- TABELLA UTENTE (DATI PROFILO)
-- ==========================
CREATE TABLE Utente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    ultimo_accesso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (email) REFERENCES Credenziali(email) ON DELETE CASCADE
);

-- ==========================
-- ALTRE TABELLE
-- ==========================

CREATE TABLE Abbonamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_utente INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    data_inizio DATE NOT NULL,
    data_fine DATE NOT NULL,
    stato ENUM('attivo', 'scaduto', 'in_attesa_pagamento') NOT NULL,
    riferimento_pagamento VARCHAR(100),
    FOREIGN KEY (id_utente) REFERENCES Utente(id)
);

CREATE TABLE Attivita (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descrizione TEXT,
    data DATE NOT NULL,
    ora_inizio TIME NOT NULL,
    ora_fine TIME NOT NULL,
    posti_disponibili INT NOT NULL,
    trainer_name VARCHAR(100) NOT NULL
);

CREATE TABLE Prenotazione (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_attivita INT NOT NULL,
    id_cliente INT NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    stato ENUM('completata', 'cancellata', 'futura') DEFAULT 'futura',
    FOREIGN KEY (id_attivita) REFERENCES Attivita(id) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES Utente(id) ON DELETE CASCADE
);

CREATE TABLE Scheda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    nome_scheda VARCHAR(100),
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES Utente(id)
);

CREATE TABLE EsercizioScheda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_scheda INT NOT NULL,
    nome_esercizio VARCHAR(100),
    serie INT NOT NULL,
    ripetizioni INT NOT NULL,
    note VARCHAR(100),
    FOREIGN KEY (id_scheda) REFERENCES Scheda(id)
);

CREATE TABLE Report (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titolo VARCHAR(100),
    contenuto TEXT,
    data_generazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================
-- TABELLA COMUNICAZIONE (richiesta dalla procedura)
-- ==========================
CREATE TABLE Comunicazione (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titolo VARCHAR(100),
    contenuto TEXT,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================
-- LOG EMAIL SIMULATO
-- ==========================

CREATE TABLE EmailLog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destinatario VARCHAR(100),
    oggetto VARCHAR(100),
    corpo TEXT,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================
-- TRIGGERS
-- ==========================

DELIMITER //

CREATE TRIGGER aggiorna_posti_disponibili
AFTER INSERT ON Prenotazione
FOR EACH ROW
BEGIN
    UPDATE Attivita
    SET posti_disponibili = posti_disponibili - 1
    WHERE id = NEW.id_attivita;
END;
//

CREATE TRIGGER verifica_posti_disponibili
BEFORE INSERT ON Prenotazione
FOR EACH ROW
BEGIN
    DECLARE posti INT;

    SELECT posti_disponibili INTO posti
    FROM Attivita
    WHERE id = NEW.id_attivita;

    IF posti <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Posti non disponibili per questa attività';
    END IF;
END;
//

CREATE TRIGGER aggiorna_stato_abbonamento_insert
BEFORE INSERT ON Abbonamento
FOR EACH ROW
BEGIN
    IF CURDATE() > NEW.data_fine THEN
        SET NEW.stato = 'scaduto';
    ELSE
        SET NEW.stato = 'attivo';
    END IF;
END;
//

CREATE TRIGGER aggiorna_stato_abbonamento_update
BEFORE UPDATE ON Abbonamento
FOR EACH ROW
BEGIN
    IF CURDATE() > NEW.data_fine THEN
        SET NEW.stato = 'scaduto';
    ELSE
        SET NEW.stato = 'attivo';
    END IF;
END;
//

CREATE TRIGGER invia_email_prenotazione
AFTER UPDATE ON Prenotazione
FOR EACH ROW
BEGIN
    INSERT INTO EmailLog (destinatario, oggetto, corpo)
    VALUES (
        (SELECT email FROM Utente WHERE id = NEW.id_cliente),
        'Prenotazione Confermata',
        CONCAT('La tua prenotazione per l\ attività ', NEW.id_attivita, ' è stata confermata.')
    );
END;
//

DELIMITER ;

-- ==========================
-- PROCEDURE
-- ==========================

DELIMITER //

DROP PROCEDURE IF EXISTS login;
//
CREATE PROCEDURE login(
    IN p_email VARCHAR(50),
    IN p_password_hash VARCHAR(255),
    OUT p_ruolo INT
)
BEGIN
    DECLARE temp_ruolo INT;

    SELECT ruolo INTO temp_ruolo
    FROM Credenziali
    WHERE email = p_email AND password_hash = p_password_hash;

    IF temp_ruolo IS NULL THEN
        SET p_ruolo = 0;
    ELSE
        SET p_ruolo = temp_ruolo;
    END IF;
END;
//

DROP PROCEDURE IF EXISTS RinnovaAbbonamento;
//
CREATE PROCEDURE RinnovaAbbonamento(
    IN p_id_utente INT,
    IN p_data_inizio DATE,
    IN p_data_fine DATE,
    IN p_rif_pagamento VARCHAR(100),
    IN p_prezzo DECIMAL(6,2)
)
BEGIN
    INSERT INTO Abbonamento(id_utente, data_inizio, data_fine, stato, riferimento_pagamento, data_rinnovo, prezzo)
    VALUES (p_id_utente, p_data_inizio, p_data_fine, 'attivo', p_rif_pagamento, CURDATE(), p_prezzo);
END;
//

DROP PROCEDURE IF EXISTS ConfermaPrenotazione;
//
CREATE PROCEDURE ConfermaPrenotazione(
    IN p_id_prenotazione INT
)
BEGIN
    UPDATE Prenotazione
    SET stato = 'completata'
    WHERE id = p_id_prenotazione;
END;
//

DROP PROCEDURE IF EXISTS InviaComunicazioneGlobale;
//
CREATE PROCEDURE InviaComunicazioneGlobale(
    IN p_titolo VARCHAR(100),
    IN p_contenuto TEXT
)
BEGIN
    INSERT INTO Comunicazione(titolo, contenuto)
    VALUES(p_titolo, p_contenuto);

    INSERT INTO EmailLog (destinatario, oggetto, corpo)
    SELECT email, p_titolo, p_contenuto
    FROM Utente
    JOIN Credenziali ON Utente.email = Credenziali.email
    WHERE ruolo = 1;
END;
//

DELIMITER ;

-- ==========================
-- UTENTI MYSQL E PRIVILEGI
-- ==========================

CREATE USER IF NOT EXISTS 'login'@'localhost' IDENTIFIED BY 'login123!';
CREATE USER IF NOT EXISTS 'cliente'@'localhost' IDENTIFIED BY 'cliente123!';
CREATE USER IF NOT EXISTS 'trainer'@'localhost' IDENTIFIED BY 'trainer123!';
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin123!';

GRANT EXECUTE ON PROCEDURE PalestraDB.login TO 'login'@'localhost';

-- Credenziali e Utente
GRANT SELECT ON PalestraDB.Credenziali TO 'cliente'@'localhost';
GRANT SELECT ON PalestraDB.Utente TO 'cliente'@'localhost';

-- Attività e prenotazioni
GRANT SELECT ON PalestraDB.Attivita TO 'cliente'@'localhost';
GRANT SELECT, INSERT ON PalestraDB.Prenotazione TO 'cliente'@'localhost';

-- Abbonamenti
GRANT SELECT, INSERT, UPDATE ON PalestraDB.Abbonamento TO 'cliente'@'localhost';

-- Visualizzazione schede e esercizi
GRANT SELECT ON PalestraDB.Scheda TO 'cliente'@'localhost';
GRANT SELECT ON PalestraDB.EsercizioScheda TO 'cliente'@'localhost';

-- Procedure
GRANT EXECUTE ON PROCEDURE PalestraDB.login TO 'cliente'@'localhost';
GRANT EXECUTE ON PROCEDURE PalestraDB.RinnovaAbbonamento TO 'cliente'@'localhost';


-- Credenziali e utenti
GRANT SELECT ON PalestraDB.Credenziali TO 'trainer'@'localhost';
GRANT SELECT, INSERT ON PalestraDB.Utente TO 'trainer'@'localhost';

-- Schede e esercizi: pieno accesso
GRANT SELECT, INSERT, UPDATE, DELETE ON PalestraDB.Scheda TO 'trainer'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON PalestraDB.EsercizioScheda TO 'trainer'@'localhost';

-- Attività (per creazione e gestione sessioni)
GRANT SELECT, INSERT, UPDATE ON PalestraDB.Attivita TO 'trainer'@'localhost';

-- Procedure
GRANT EXECUTE ON PROCEDURE PalestraDB.login TO 'trainer'@'localhost';


GRANT ALL PRIVILEGES ON PalestraDB.* TO 'admin'@'localhost';

FLUSH PRIVILEGES;

-- ==========================
-- DATI DI TEST
-- ==========================

-- SHA256("1234") = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4

INSERT INTO Credenziali (email, password_hash, ruolo) VALUES
('cliente1@example.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1),
('trainer1@example.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 2),
('admin1@example.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 3);

INSERT INTO Utente (username, email) VALUES
('cliente1', 'cliente1@example.com'),
('trainer1', 'trainer1@example.com'),
('admin1', 'admin1@example.com');
