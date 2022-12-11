public class ThreadMessageBob implements Runnable{

        BobServer bob = new BobServer();
    @Override
    public void run() {
        try {
            bob.exchangeMessageBob(Main.port2, "Hi, Alice!", String.valueOf(Main.resBob));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
