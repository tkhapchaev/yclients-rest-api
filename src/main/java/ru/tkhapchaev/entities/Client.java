package ru.tkhapchaev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "client", schema = "activities")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yclients_id")
    private Long yclientsId;

    private String name;

    private String phone;

    @Column(name = "yclients_activity_id")
    private Long yclientsActivityId;

    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Client)) {
            return false;
        }

        final Client other = (Client) object;

        if (!other.canEqual((Object) this)) {
            return false;
        }

        final Object this$yclientsId = this.getYclientsId();
        final Object other$yclientsId = other.getYclientsId();

        if (this$yclientsId == null ? other$yclientsId != null : !this$yclientsId.equals(other$yclientsId)) {
            return false;
        }

        final Object this$name = this.getName();
        final Object other$name = other.getName();

        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }

        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();

        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) {
            return false;
        }

        final Object this$competitionId = this.getYclientsActivityId();
        final Object other$competitionId = other.getYclientsActivityId();

        if (this$competitionId == null ? other$competitionId != null : !this$competitionId.equals(other$competitionId)) {
            return false;
        }

        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Client;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;

        final Object $identifier = this.getId();
        result = result * PRIME + ($identifier == null ? 43 : $identifier.hashCode());

        final Object $yclientsId = this.getYclientsId();
        result = result * PRIME + ($yclientsId == null ? 43 : $yclientsId.hashCode());

        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());

        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());

        final Object $competitionId = this.getYclientsActivityId();
        result = result * PRIME + ($competitionId == null ? 43 : $competitionId.hashCode());

        return result;
    }
}