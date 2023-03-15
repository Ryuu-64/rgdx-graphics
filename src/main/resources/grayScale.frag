#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords) * v_color;
    float grayScale = (color.r * 0.299 + color.g * 0.587 + color.b * 0.114);
    gl_FragColor = vec4(grayScale, grayScale, grayScale, color.a);
}