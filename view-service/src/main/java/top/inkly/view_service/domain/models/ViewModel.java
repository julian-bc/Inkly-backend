package top.inkly.view_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewModel {
    private String viewId;
    private Long readerCounter;
    private Long newReaders;
    private BookModel book;

    public String getEmailReceiver() {
        return book.getAuthorEmail();
    }

    public Map<String, String> preparedData() {
        Map<String, String> data = new HashMap<>();

        data.put("readerCounter", String.valueOf(this.readerCounter));
        data.put("newReaders", String.valueOf(this.newReaders));
        data.put("bookName", book.getTitle());

        return data;
    }
}