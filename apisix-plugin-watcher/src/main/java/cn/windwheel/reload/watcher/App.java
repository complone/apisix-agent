package cn.windwheel.reload.watcher;

import cn.windwheel.reload.common.AnsiLog;
import cn.windwheel.reload.watcher.command.CommandListener;

/**
 * @author windwheel
 */
public class App {

    private boolean running = false;
    private Watcher watcher;
    private CommandListener commandListener;

    private static final App app = new App();

    public static App me() {
        return app;
    }

    public void reload() {
        if (watcher == null) {
            AnsiLog.warn("watcher not start");
            return;
        }
        watcher.reloadClass();
    }

    public void start() {
        running = true;
        startWatcher();
        startCommandListener();
    }

    private void startWatcher() {
        watcher = new Watcher();
        watcher.start();
    }

    private void startCommandListener() {
        commandListener = new CommandListener();
        commandListener.listen();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        watcher.stop();
        System.exit(0);
    }

    public boolean isRunning() {
        return running;
    }


}
