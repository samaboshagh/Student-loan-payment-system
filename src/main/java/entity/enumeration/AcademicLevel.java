package entity.enumeration;

import lombok.Getter;

@Getter
public enum AcademicLevel {

    KARDANI(2),
    CONTINUOUS_BACHELOR(4),
    DISCONTINUOUS_BACHELOR(4),
    CONTINUOUS_MASTER(6),
    DISCONTINUOUS_MASTER(2),
    DOCTORATE(5),
    CONTINUOUS_DOCTORATE(5),
    DISCONTINUOUS_SPECIALIZED_DOCTORATE(5);

    final int graduationYear ;

    AcademicLevel(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}