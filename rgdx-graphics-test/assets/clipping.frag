#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_bottomLeft;
varying vec2 v_topRight;
uniform sampler2D u_texture;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords) * v_color;
    color *= step(v_bottomLeft.x, v_texCoords.x) * step(v_texCoords.x, v_topRight.x);
    color *= step(v_texCoords.y, 1.0 - v_bottomLeft.y) * step(1.0 - v_topRight.y, v_texCoords.y);
    gl_FragColor = color;
}