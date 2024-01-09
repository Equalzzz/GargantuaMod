attribute vec4 a_position;
attribute vec3 a_normal;
attribute vec4 a_color;

uniform mat4 u_proj;
uniform mat4 u_trans;
uniform vec3 u_lightdir;
uniform vec3 u_camdir;
uniform vec3 u_campos;
uniform vec3 u_ambientColor;
uniform float u_time;

varying vec4 v_col;

const vec3 diffuse = vec3(0.01);

//Bulk made by Anuke(n)
void main(){
    vec3 specular = vec3(0.0, 0.0, 0.0);
    vec3 norm = normalize(a_position.xyz);
    mat4 time_trans = mat4(1.0, 0.0, 0.0, (sin(u_time/10 + norm.x * 67339) + 1) * norm.x/20,
                           0.0, 1.0, 0.0, (sin(u_time/10 + norm.y * 23227) + 1) * norm.y/20,
                           0.0, 0.0, 1.0, (sin(u_time/10 + norm.z * 49919) + 1) * norm.z/20,
                           0.0, 0.0, 0.0, 1.0);
    a_position *= time_trans;
    vec3 lightReflect = normalize(reflect((a_normal + norm) * 0.5, u_lightdir));
    vec3 vertexEye = normalize(u_campos - (u_trans * a_position).xyz/2.0);
    float specularFactor = dot(vertexEye, lightReflect);
    if(specularFactor > 0.0){
        specular = vec3(1.0 * pow(specularFactor, 40.0)) * (1.0-a_color.a);
    }

	vec3 norc = (u_ambientColor + specular) * (diffuse + vec3(clamp((dot(a_normal, u_lightdir) + 1.0) / 2.0, 0.0, 1.0)));

    v_col.rgb = vec4(a_color.rgb * (specularFactor + 1)/2.0, 1.0);
    gl_Position = u_proj * u_trans * a_position;
}