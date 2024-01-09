package gargantua.content;

import arc.graphics.Color;
import gargantua.graphics.GargantuaShaders;
import gargantua.planets.GargantuaPlanetGenerator;
import mindustry.content.Planets;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexMesher;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.type.Planet;

public class GargantuaPlanets {
    public static Planet gargantua;
    public static void load() {
        gargantua = new Planet("gargantua", Planets.sun, 1f, 2) {
            {
                accessible = true;
                alwaysUnlocked = true;
                iconColor = Color.valueOf("d1a77f");
                landCloudColor = Color.valueOf("dcd0b8").cpy();
                atmosphereColor = Color.valueOf("d1a77f").cpy();
                atmosphereRadOut = 0.3f;
                atmosphereRadIn = 0.1f;
                orbitTime = 1.0f;
                hasAtmosphere = true;
                /*
                cloudMeshLoader = () -> new MultiMesh(
                        new HexSkyMesh(this, 2, 2.33f, 0.14f, 5, Color.valueOf("eba768").a(0.75f), 2, 0.42f, 1f, 0.43f),
                        new HexSkyMesh(this, 3, 2f, 0.15f, 5, Color.valueOf("eea293").a(0.75f), 2, 0.42f, 1.2f, 0.45f)
                );
                */
                generator = new GargantuaPlanetGenerator();
                meshLoader = () -> new HexMesh(this, this.generator,4, GargantuaShaders.gargantuaPlanet);
            }
        };
    }
}
