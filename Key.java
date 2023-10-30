public class Key extends Item {
    Tier tier;

    /**
     * Constructor for item
     *
     * @param tier tier of the key
     */
    public Key(Tier tier) {
        super(tier + " Key", "Key", "A key used to unlock " + tier.toString().toLowerCase() + " doors.", Math.PI);
        this.tier = tier;
    }

    /**
     * Getter for tier
     *
     * @return tier of key
     */
    public Tier getTier() {
        return tier;
    }

    public enum Tier {
        COPPER("Copper"), IRON("Iron"), GOLD("Gold");
        // The tier string.
        private String tier;

        /**
         * Initialise with the corresponding tier string.
         *
         * @param tier The tier of the key.
         */
        Tier(String tier) {
            this.tier = tier;
        }

        /**
         * @return The key tier as a string.
         */
        public String toString() {
            return tier;
        }
    }

}
