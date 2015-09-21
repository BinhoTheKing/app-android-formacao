package br.com.cast.turmaformacao.mytaskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private Long id;
    private String name;
    private String description;
    private Label label;

    public Task() {
        super();
    }

    protected Task(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Label getLabel() {
        return this.label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        return !(description != null ? !description.equals(task.description) : task.description != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id == null ? -1 : this.id);
        dest.writeString(this.name == null ? "" : this.name);
        dest.writeString(this.description == null ? "" : this.description);
    }

    public void readFromParcel(Parcel inp) {

        this.id = inp.readLong();
        this.id = this.id == -1 ? null : this.id;
        this.name = inp.readString();
        this.description = inp.readString();
    }
}


