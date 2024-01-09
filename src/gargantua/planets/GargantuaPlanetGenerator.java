package gargantua.planets;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import arc.util.noise.Simplex;
import mindustry.maps.generators.PlanetGenerator;

public class GargantuaPlanetGenerator extends PlanetGenerator {
    @Override
    public float getHeight(Vec3 position) {
        return 0;
    }
    @Override
    public Color getColor(Vec3 position) {
        float tint = Simplex.noise3d(0, 4, 0.22f, 2, position.x/100f, position.y, position.z);
        tint = (tint + 1f)/2f;
        Color col = Color.valueOf("ddb47e").mul(tint).cpy();
        col.a = 0.5f;
        return col;
    }
}
