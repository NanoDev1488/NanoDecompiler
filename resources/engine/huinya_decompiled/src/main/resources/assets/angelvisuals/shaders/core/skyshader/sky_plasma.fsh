#version 150

uniform vec4 u_Color;
uniform vec4 u_Color2;
uniform vec2 u_Resolution;
uniform float u_Scale;
uniform float u_Time;
uniform float u_Fov;
uniform vec2 u_CameraDir;

out vec4 fragColor;

#define PI 3.14159265359

mat3 rotX(float a) {
    float c = cos(a), s = sin(a);
    return mat3(1.0, 0.0, 0.0,
                0.0,   c,   s,
                0.0,  -s,   c);
}

mat3 rotY(float a) {
    float c = cos(a), s = sin(a);
    return mat3(  c, 0.0,   s,
                0.0, 1.0, 0.0,
                 -s, 0.0,   c);
}

void main() {
    vec2 uv = gl_FragCoord.xy / u_Resolution.xy;
    vec2 sp = uv * 2.0 - 1.0;
    float aspect = u_Resolution.x / u_Resolution.y;

    float tanV = tan(radians(u_Fov) * 0.5);
    vec3 rayView = normalize(vec3(sp.x * tanV * aspect, sp.y * tanV, 1.0));
    vec3 ray = rotY(u_CameraDir.x) * rotX(u_CameraDir.y) * rayView;

    vec3 p = ray * u_Scale;
    float time = u_Time * 1.5;

    float v1 = sin(p.x + time) + sin(p.y + time) + sin(p.z + time);

    float v2 = sin(p.x * cos(time * 0.4) - p.z * sin(time * 0.4) + time)
             + sin(p.y * sin(time * 0.3) + p.z * cos(time * 0.3) + time);

    float v3 = sin(length(p * 1.2) - time * 1.2);

    float v = (v1 + v2 + v3) / 3.0;
    float norm = v * 0.5 + 0.5;

    vec3 bgColor = mix(u_Color.rgb, u_Color2.rgb, 0.35) * 0.14;
    vec3 plasmaColor = mix(u_Color.rgb, u_Color2.rgb, clamp(norm * 0.82 + 0.08, 0.0, 1.0));
    plasmaColor *= 0.45 + norm * 0.55;
    vec3 ridgeColor = mix(u_Color.rgb, u_Color2.rgb, 0.5) * pow(norm, 5.0) * 1.25;

    vec3 finalColor = mix(bgColor, plasmaColor, norm) + ridgeColor;
    finalColor = clamp(finalColor, 0.0, 1.0);

    fragColor = vec4(finalColor, u_Color.a);
}