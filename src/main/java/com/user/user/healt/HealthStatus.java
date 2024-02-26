package com.name_template.name_template.healt;

public enum HealthStatus {
    HEALTHY(1),
    UNHEALTHY(2),
    UNKNOWN(3);

    private final int i;

    HealthStatus(int i) {
        this.i = i ;
    }

    public int getId(){
        return this.i;
    }
}
