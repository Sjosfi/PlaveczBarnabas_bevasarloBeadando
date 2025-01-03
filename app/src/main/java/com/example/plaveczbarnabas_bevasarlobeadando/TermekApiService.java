import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface TermekApiService {
    @GET("termekek")
    Call<List<Termekek>> getTermekek();

    @POST("termekek")
    Call<Void> createTermek(@Body Termekek termek);

    @PUT("termekek/{id}")
    Call<Void> updateTermek(@Path("id") int id, @Body Termekek termek);

    @DELETE("termekek/{id}")
    Call<Void> deleteTermek(@Path("id") int id);
}