#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec4 v_hdrColor;
varying vec2 v_texCoords;
varying float v_intensity;
uniform sampler2D u_texture;

void main() {
    vec4 hdrColor = v_hdrColor * v_intensity;
    vec4 color = texture2D(u_texture, v_texCoords) * v_color * hdrColor;
    gl_FragColor = color;
}