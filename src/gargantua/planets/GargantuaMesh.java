package gargantua.planets;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.util.noise.Simplex;
import mindustry.content.Planets;
import mindustry.graphics.Shaders;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexMesher;
import mindustry.type.Planet;

public class GargantuaMesh extends HexMesh {
    public GargantuaMesh(Planet planet, int divisions, double octaves, double persistence, double scl, double pow, double mag){
        super(planet, new HexMesher(){
            @Override
            public float getHeight(Vec3 position) {
                return 0;
            }

            @Override
            public Color getColor(Vec3 position) {
                double height = Math.pow(Simplex.noise3d(0, octaves, persistence, scl, position.x, position.y, position.z), pow) * mag;
                Color c = new Color(1/209f,1/167f,1/127f);
                return c.mul((float)Mathf.clamp(height,0, 1));
            }
        }, divisions, Shaders.unlit);
    }
}
