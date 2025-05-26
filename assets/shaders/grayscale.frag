#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform float u_grayness; // 0 = normal, 1 = full grayscale

void main() {
    vec4 color = texture2D(u_texture, v_texCoords);
    float gray = dot(color.rgb, vec3(0.299, 0.587, 0.114)); // Converts to grayscale
    gl_FragColor = mix(color, vec4(gray, gray, gray, color.a), u_grayness);
}
