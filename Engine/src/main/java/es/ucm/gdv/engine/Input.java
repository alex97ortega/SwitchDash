package es.ucm.gdv.engine;
import java.util.List;

public interface Input {
    class TouchEvent {
        public TouchEvent(Type type, int x, int y)
        {
            this.type = type;
            this.x = x;
            this.y = y;
        }
        public float x;
        public float y;
        public enum Type{Pressed, Released}
        public Type type;
    }
    List<TouchEvent> getTouchEvents();
    void deleteEvents();
}
