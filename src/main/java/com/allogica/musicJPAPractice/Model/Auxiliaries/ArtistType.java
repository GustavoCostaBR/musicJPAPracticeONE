package com.allogica.musicJPAPractice.Model.Auxiliaries;

public enum ArtistType {

        SOLO("Solo"),
        DUO("Duo"),
        BAND("Band");

        private final String description;

        ArtistType(String description) {
            this.description = description;
        }

    public static boolean contains(String type) {
        for (ArtistType artistType : ArtistType.values()) {
            if (artistType.name().equals(type.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return this.description;
        }


}
