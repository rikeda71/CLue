package api.clue.repository.mapper.sqlprovider;

import java.util.regex.Pattern;

public abstract class DynamicSqlProvider {
    private Pattern urlPattern =
            Pattern.compile("^https?://[a-z\\\\.:/\\\\+\\\\-\\\\#\\\\?\\\\=\\\\&\\\\;\\\\%\\\\~]+$");

    protected Boolean isURL(String urlCandidate) {
        return urlPattern.matcher(urlCandidate).matches();
    }
}
