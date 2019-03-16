CREATE TABLE ANSATT(
                     ID                        NUMBER GENERATED ALWAYS AS IDENTITY,
                     NAVN                       VARCHAR2(255)
);


CREATE TABLE KUNDE(
                     ID                        NUMBER GENERATED ALWAYS AS IDENTITY,
                     NAVN                       VARCHAR2(255)
);

CREATE TABLE PRODUKT (
                     ID                        NUMBER GENERATED ALWAYS AS IDENTITY,
                     BESKRIVELSE               VARCHAR2(255),
                     PRIS                      NUMBER
);


CREATE TABLE SALG(
                     ID                        NUMBER GENERATED ALWAYS AS IDENTITY,
                     ANTALL                    NUMBER,
                     TOTAL_PRIS                 NUMBER,
                     SELGER                     NUMBER,
                     KUNDE                      NUMBER,
                     PRODUKT                    NUMBER
);