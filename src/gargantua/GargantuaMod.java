package gargantua;

import arc.*;
import arc.util.*;
import gargantua.content.GargantuaPlanets;
import gargantua.graphics.GargantuaShaders;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class GargantuaMod extends Mod{

    public GargantuaMod(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'gargantua-mod' in its config)
                dialog.cont.image(Core.atlas.find("gargantua-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        GargantuaPlanets.load();
        GargantuaShaders.init();
    }

}
