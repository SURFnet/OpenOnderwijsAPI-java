package nl.surfnet.oda.affiliations;

import java.util.List;

/**
 * Represents an affiliation.
 *
 * @author Daniel Zolnai
 *
 */
public class Affiliation {

    private List<String> _personUrls;
    private String _resourceUrl;
    private String _affiliation;

    /**
     * Empty constructor needed for conversion.
     */
    public Affiliation() {
    }

    public List<String> getPersonUrls() {
        return _personUrls;
    }

    public void setPersonsUrls(List<String> personUrls) {
        _personUrls = personUrls;
    }

    public String getResourceUrl() {
        return _resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        _resourceUrl = resourceUrl;
    }

    public String getAffiliation() {
        return _affiliation;
    }

    public void setAffiliation(String affiliation) {
        _affiliation = affiliation;
    }

}
