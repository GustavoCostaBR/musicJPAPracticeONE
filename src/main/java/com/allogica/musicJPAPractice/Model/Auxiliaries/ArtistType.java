package com.allogica.musicJPAPractice.Model.Auxiliaries;

public enum ArtistType {

        SOLO("Solo"),
        DUO("Duo"),
        BAND("Band");

        private final String description;

        ArtistType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return this.description;
        }


}
