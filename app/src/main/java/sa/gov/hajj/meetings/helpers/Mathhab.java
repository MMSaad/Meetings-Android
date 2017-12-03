package sa.gov.hajj.meetings.helpers;

/**
 * Mathhab is used for Assr prayer calculation.
 */
public class Mathhab {
    /**
     * Assr prayer shadow ratio: use Shaa'fi mathhab (default)
     */
    public static final Mathhab SHAAFI = new Mathhab();
    /**
     * Assr prayer shadow ratio: use Hanafi mathhab
     */
    public static final Mathhab HANAFI = new Mathhab();

    private Mathhab() {

    }


}
