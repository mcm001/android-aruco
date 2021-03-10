package min3d.interfaces;

public interface IDirtyManaged {
    boolean isDirty();

    void setDirtyFlag();

    void clearDirtyFlag();
}
