package br.com.cast.turmaformacao.mytaskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {
    private Long id;
    private Long webId;
    private String name;
    private String password;
    private List<Task> tasks;
	private Address address;
    public static String PARAM_USER = "USER";

    public User(){
        super();
        this.tasks = new ArrayList<>();
    }
    public User(Long id, String name, String password) {
        this();
        this.id = id;
        this.name = name;
        this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String $Password) {
		password = $Password;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> $Tasks) {
		tasks = $Tasks;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address $Address) {
		address = $Address;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.password);
        dest.writeTypedList(tasks);
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.password = in.readString();
        this.tasks = in.createTypedArrayList(Task.CREATOR);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
