package sa.gov.hajj.meetings.interfaces;

/**
 * Created by mustafa on 8/3/17.
 * Release the GEEK
 */

public interface IDuaaClickListener extends IClickListener {
    void DownloadDuaa(long id);

    void PlayDuaa(long id);

    void DoaaAlert(boolean alert);

    void DuaaBookmark(boolean favorites);
}
