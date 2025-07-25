package ispwproject.gymwizard.util.observer.observers;

import ispwproject.gymwizard.util.logger.AppLogger;

public abstract class Observer implements Runnable {

    protected long timeOut;
    protected boolean isAlive;

    protected Observer(long timeOut) {
        this.timeOut = timeOut;
        this.isAlive = false;
    }

    @Override
    public void run() {
        this.isAlive = true;
        while (this.isAlive) {
            this.update();
            try {
                Thread.sleep(this.timeOut);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // <--- Ripristina lo stato di interruzione
                AppLogger.logError("Osservazione interrotta: " + e.getMessage());
                this.isAlive = false; // Termina l’osservazione in modo pulito
            }
        }
    }


    public void stopObservation() {
        this.isAlive = false;
    }

    protected void notifySubjectStatus(String message) {
        AppLogger.getLogger().info(message);
    }

    public abstract void update();
}
