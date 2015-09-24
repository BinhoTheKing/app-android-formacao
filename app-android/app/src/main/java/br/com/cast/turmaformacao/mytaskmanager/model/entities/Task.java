package br.com.cast.turmaformacao.mytaskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task implements Parcelable {
	@JsonIgnore
    private Long id;
	@JsonProperty("_id")
    private Long webId;
    private String name;
    private String description;
	@JsonIgnore
    private Label label;
	@JsonIgnore
    private User user;

    public static final String PARAM_TASK = "PARAM_TASK";

    public Task() {
        super();
        this.label = new Label();
        this.user = new User();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long $Id) {
		id = $Id;
	}

	public Long getWebId() {
		return webId;
	}

	public void setWebId(Long $WebId) {
		webId = $WebId;
	}

	public String getName() {
		return name;
	}

	public void setName(String $Name) {
		name = $Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String $Description) {
		description = $Description;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label $Label) {
		label = $Label;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User $User) {
		user = $User;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!name.equals(task.name)) return false;
        if (!description.equals(task.description)) return false;
        if (!label.equals(task.label)) return false;
        return user.equals(task.user);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + user.hashCode();
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

    public void readFromParcel(Parcel in) {
        this.id = in.readLong();
        this.id = this.id == -1 ? null : this.id;
        this.name = in.readString();
        this.description = in.readString();
        this.label = in.readParcelable(Label.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
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
        dest.writeParcelable(this.label == null ? new Label() : this.label, flags);
        dest.writeParcelable(this.user == null ? new User() : this.user, flags);
    }

    protected Task(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}


