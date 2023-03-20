package ru.tkhapchaev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "activity", schema = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yclients_id")
    private Long yclientsId;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Activity)) {
            return false;
        }

        final Activity other = (Activity) object;

        if (!other.canEqual((Object) this)) {
            return false;
        }

        final Object this$yclientsId = this.getYclientsId();
        final Object other$yclientsId = other.getYclientsId();

        if (this$yclientsId == null ? other$yclientsId != null : !this$yclientsId.equals(other$yclientsId)) {
            return false;
        }

        final Object this$date = this.getDateTime();
        final Object other$date = other.getDateTime();

        if (this$date == null ? other$date != null : !this$date.equals(other$date)) {
            return false;
        }

        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Activity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;

        final Object $identifier = this.getId();
        result = result * PRIME + ($identifier == null ? 43 : $identifier.hashCode());

        final Object $yclientsId = this.getYclientsId();
        result = result * PRIME + ($yclientsId == null ? 43 : $yclientsId.hashCode());

        final Object $date = this.getDateTime();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());

        return result;
    }
}