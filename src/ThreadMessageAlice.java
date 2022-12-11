public class ThreadMessageAlice implements Runnable{

        AliceClient alice = new AliceClient();
    @Override
    public void run() {
        try {
            alice.exchangeMsgAlice(Main.port, "Hi, Bob!", String.valueOf(Main.resAlice));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
