public class ThreadMessageEveAliceRole implements Runnable{

        EveClient eve = new EveClient();
    @Override
    public void run() {
        try {
            eve.exchangeMsgAlice(Main.port2, "You an asshole, Bob", String.valueOf(Main.resEve));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
