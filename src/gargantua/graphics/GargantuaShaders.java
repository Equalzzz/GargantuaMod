package gargantua.graphics;

import arc.Core;
import arc.graphics.Camera;
import arc.graphics.Color;
import arc.graphics.gl.Shader;
import arc.math.geom.Vec3;
import arc.util.Time;
import gargantua.content.GargantuaPlanets;
import mindustry.content.Planets;
import mindustry.graphics.g3d.PlanetParams;
import mindustry.type.Planet;

import static mindustry.Vars.renderer;
import static mindustry.Vars.tree;

public class GargantuaShaders {
    public static GargantuaPlanetShader gargantuaPlanet;
    public static void init(){
        gargantuaPlanet = new GargantuaPlanetShader();
    }
    public static class GargantuaPlanetShader extends Shader {
        public Planet planet = GargantuaPlanets.gargantua;
        public Vec3 lightDir = new Vec3(-1, 0, -1).nor();
        public Color ambientColor = Color.white.cpy();
        public Vec3 camDir = new Vec3();

        public GargantuaPlanetShader(){
            super(tree.get("shaders/gargantuaplanet.vert"),Core.files.internal("shaders/planet.frag"));
        }
        @Override
        public void apply(){
            camDir.set(renderer.planets.cam.direction).rotate(Vec3.Y, planet.getRotation());
            setUniformf("u_lightdir", lightDir);
            setUniformf("u_ambientColor", ambientColor.r, ambientColor.g, ambientColor.b);
            setUniformf("u_camdir", camDir);
            setUniformf("u_campos", renderer.planets.cam.position);
            setUniformf("u_time", Time.time);
        }
    }
}
