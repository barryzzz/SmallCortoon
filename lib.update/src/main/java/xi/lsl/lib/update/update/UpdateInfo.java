package xi.lsl.lib.update.update;

import java.util.List;

/**
 * Created by lishoulin on 2017/2/18.
 */

public class UpdateInfo {

    public Manifest manifest;
    public List<Update> updates;

    public class Manifest {
        public String version;
        public List<Bundles> bundles;
    }
}
