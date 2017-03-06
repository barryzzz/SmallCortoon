package xi.lsl.code.lib.utils.net;

import retrofit2.http.GET;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.Slide;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public interface CommonApis {
    @GET("imgs.html")
    Observable<Slide> getSlide();

    String URL_IMG_CHAPTER = "http://www.ishuhui.net/ReadComicBooksToIso/";  //漫画内容
}
