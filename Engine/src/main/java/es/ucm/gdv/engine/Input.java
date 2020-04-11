package es.ucm.gdv.engine;
import java.util.List;

// interfaz de clase que gestiona el input mediante una lista de eventos
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
