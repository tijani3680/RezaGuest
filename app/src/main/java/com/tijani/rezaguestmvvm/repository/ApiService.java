package com.tijani.rezaguestmvvm.repository;

import com.tijani.rezaguestmvvm.model.ActivateMonths;
import com.tijani.rezaguestmvvm.model.ActivateSuites;
import com.tijani.rezaguestmvvm.model.BlockCustomer;
import com.tijani.rezaguestmvvm.model.DetailsReserv;
import com.tijani.rezaguestmvvm.model.PriceSuites;
import com.tijani.rezaguestmvvm.model.Reports;
import com.tijani.rezaguestmvvm.model.ReservInformation;
import com.tijani.rezaguestmvvm.model.ReservSuites;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.model.CheckLogin;
import com.tijani.rezaguestmvvm.model.LastCustomer;
import com.tijani.rezaguestmvvm.model.UserPass;
import com.tijani.rezaguestmvvm.model.Users;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    ApiClient client = ApiClient.CLIENT;

    @FormUrlEncoded
    @POST("CheckLogin.php")
    Single<CheckLogin> loginAcount(@Field("userName") String userName, @Field("password") String password);

    @GET("SelectAllCustomers.php")
    Single<List<LastCustomer>> getLastCustomers();

    @FormUrlEncoded
    @POST("AddCustomerToBlackList.php")
    Single<Status> addToBlackList(@Field("customerId") String customerId, @Field("description") String description);


    @GET("SelectBlackListCustomers.php")
    Single<List<BlockCustomer>> getBlockCustomer();

    @FormUrlEncoded
    @POST("DeleteCustomerFromBlackList.php")
    Single<Status> deleteCustomerFromBlackList(@Field("id") String id);

    @GET("CheckActivateSuites.php")
    Single<ActivateSuites> checkActivateSuites();

    @GET("ReadPrice.php")
    Single<List<PriceSuites>> getPriceSuites();

    @GET("CheckConditionCustomer.php")
    Single<Status> checkConditionCustomer(@Query("shenaseh") String shenaseh);

    @GET("ReadSuitesFarvardin.php")
    Single<List<ReservSuites>> getReservFarvardin();

    @GET("ReadSuitesOrdibehesht.php")
    Single<List<ReservSuites>> getReservOrdibehesht();

    @GET("ReadSuitesKhordad.php")
    Single<List<ReservSuites>> getReservKhordad();

    @GET("ReadSuitesTir.php")
    Single<List<ReservSuites>> getReservTir();

    @GET("ReadSuitesMordad.php")
    Single<List<ReservSuites>> getReservMordad();

    @GET("ReadSuitesShahrivar.php")
    Single<List<ReservSuites>> getReservShahrivar();

    @GET("ReadSuitesMehr.php")
    Single<List<ReservSuites>> getReservMehr();

    @GET("ReadSuitesAban.php")
    Single<List<ReservSuites>> getReservAban();

    @GET("ReadSuitesAzar.php")
    Single<List<ReservSuites>> getReservAzar();

    @GET("ReadSuitesDey.php")
    Single<List<ReservSuites>> getReservDey();

    @GET("ReadSuitesBahman.php")
    Single<List<ReservSuites>> getReservBahman();

    @GET("ReadSuitesEsfand.php")
    Single<List<ReservSuites>> getReservEsfand();

    @GET("ReadReservationFarvardin.php")
    Single<List<ReservInformation>> getAllReservFarvardin();


    @GET("ReadReservationOrdibehesht.php")
    Single<List<ReservInformation>> getAllReservOrdibehesh();



    @GET("ReadReservationKhordad.php")
    Single<List<ReservInformation>> getAllReservKhordad();


    @GET("ReadReservationTir.php")
    Single<List<ReservInformation>> getAllReservTir();

    @GET("ReadReservationMordad.php")
    Single<List<ReservInformation>> getAllReservMordad();

    @GET("ReadReservationShahrivar.php")
    Single<List<ReservInformation>> getAllReservShahrivar();

    @GET("ReadReservationMehr.php")
    Single<List<ReservInformation>> getAllReservMehr();

    @GET("ReadReservationAban.php")
    Single<List<ReservInformation>> getAllReservAban();

    @GET("ReadReservationAzar.php")
    Single<List<ReservInformation>> getAllReservAzar();

    @GET("ReadReservationDey.php")
    Single<List<ReservInformation>> getAllReservDey();

    @GET("ReadReservationBahman.php")
    Single<List<ReservInformation>> getAllReservBahman();

    @GET("ReadReservationEsfand.php")
    Single<List<ReservInformation>> getAllReservEsfand();



    @GET("ReadDetilsSuitesCustomerFarvardin.php")
    Single<DetailsReserv> getDetailsReservFarvardin(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerOrdibehesht.php")
    Single<DetailsReserv> getDetailsReservOrdibehesht(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerKhordad.php")
    Single<DetailsReserv> getDetailsReservKhordad(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerTir.php")
    Single<DetailsReserv> getDetailsReservTir(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerMordad.php")
    Single<DetailsReserv> getDetailsReservMordad(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerShahrivar.php")
    Single<DetailsReserv> getDetailsReservShahrivar(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerMehr.php")
    Single<DetailsReserv> getDetailsReservMehr(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerAban.php")
    Single<DetailsReserv> getDetailsReservAban(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerAzar.php")
    Single<DetailsReserv> getDetailsReservAzar(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerDey.php")
    Single<DetailsReserv> getDetailsReservDey(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerBahman.php")
    Single<DetailsReserv> getDetailsReservBahman(@Query("id") String id);

    @GET("ReadDetilsSuitesCustomerEsfand.php")
    Single<DetailsReserv> getDetailsReservEsfand(@Query("id") String id);


    @FormUrlEncoded
    @POST("UpdateCoastReceivedFarvardin.php")
    Single<Status> updateCoastReceivedFarvardin(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedOrdibehesht.php")
    Single<Status> updateCoastReceivedOrdibehesht(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedKhordad.php")
    Single<Status> updateCoastReceivedKhordad(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedTir.php")
    Single<Status> updateCoastReceivedTir(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedMordad.php")
    Single<Status> updateCoastReceivedMordad(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedShahrivar.php")
    Single<Status> updateCoastReceivedShahrivar(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedMehr.php")
    Single<Status> updateCoastReceivedMehr(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedAban.php")
    Single<Status> updateCoastReceivedAban(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedAzar.php")
    Single<Status> updateCoastReceivedAzar(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedDey.php")
    Single<Status> updateCoastReceivedDey(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedBahman.php")
    Single<Status> updateCoastReceivedBahman(@Field("id") String id,@Field("money") String money);

    @FormUrlEncoded
    @POST("UpdateCoastReceivedEsfand.php")
    Single<Status> updateCoastReceivedEsfand(@Field("id") String id,@Field("money") String money);




    @GET("DeleteReservCustomerFarvardin.php")
    Single<Status> deleteReservCustomerFarvardin(@Query("id") String id);

    @GET("DeleteReservCustomerOrdibehesht.php")
    Single<Status> deleteReservCustomerOrdibehesht(@Query("id") String id);


    @GET("DeleteReservCustomerKhordad.php")
    Single<Status> deleteReservCustomerKhordad(@Query("id") String id);


    @GET("DeleteReservCustomerTir.php")
    Single<Status> deleteReservCustomerTir(@Query("id") String id);


    @GET("DeleteReservCustomerMordad.php")
    Single<Status> deleteReservCustomerMordad(@Query("id") String id);


    @GET("DeleteReservCustomerShahrivar.php")
    Single<Status> deleteReservCustomerShahrivar(@Query("id") String id);


    @GET("DeleteReservCustomerMehr.php")
    Single<Status> deleteReservCustomerMehr(@Query("id") String id);


    @GET("DeleteReservCustomerAban.php")
    Single<Status> deleteReservCustomerAban(@Query("id") String id);


    @GET("DeleteReservCustomerAzar.php")
    Single<Status> deleteReservCustomerAzar(@Query("id") String id);


    @GET("DeleteReservCustomerDey.php")
    Single<Status> deleteReservCustomerDey(@Query("id") String id);


    @GET("DeleteReservCustomerBahman.php")
    Single<Status> deleteReservCustomerBahman(@Query("id") String id);


    @GET("DeleteReservCustomerEsfand.php")
    Single<Status> deleteReservCustomerEsfand(@Query("id") String id);

    @FormUrlEncoded
    @POST("AddOperator.php")
    Single<Status> addOperator(@Field("fullName") String fullName,@Field("code") String code,@Field("userName") String userName,@Field("password") String password);


    @GET("ShowAllOprators.php")
    Single<List<Users>> getOperators();


    @GET("DeleteOprator.php")
    Single<Status> deleteOperator(@Query("id")String id);

    @GET("ActivateSuite.php")
    Single<Status> activateSuite(@Query("suite")String suite);

    @GET("InActiveSuite.php")
    Single<Status> inActiveSuite(@Query("suite")String suite);

    @FormUrlEncoded
    @POST("UpdateSuitesPrice.php")
    Single<Status> updaptePrice(@Field("id") String id,@Field("spacialPrice") String spacialPrice,@Field("spacialMazadPrice") String spacialMazadPrice,@Field("price") String price,@Field("mazadPrice") String mazadPrice);

    @GET("CheckActivateMonths.php")
    Single<ActivateMonths> checkActivateMonths();

    @GET("ActivateMonth.php")
    Single<Status> activateMonth(@Query("month")String month);

    @GET("InActiveMonth.php")
    Single<Status> inActiveMonth(@Query("month")String month);

    @GET("Reports.php")
    Single<Reports> getReports();

    @GET("ResetMonth.php")
    Single<Status> reset(@Query("month")String month);

    @FormUrlEncoded
    @POST("GetUserPass.php")
    Single<UserPass> getUserPass(@Field("userName") String userName);

    @FormUrlEncoded
    @POST("ChangeUserPass.php")
    Single<Status> changeUserPass(@Field("oldUserName") String oldUserName,@Field("newUserName") String newUserName,@Field("newPassword") String newPassword);












    @FormUrlEncoded
    @POST("SaveReservFarvardin.php")
    Single<Status> sendReservInformationFarvardin(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                  @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                  @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                  @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                  @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                  @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                  @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);


    @FormUrlEncoded
    @POST("SaveReservOrdibehesht.php")
    Single<Status> sendReservInformationOrdibehesht(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                  @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                  @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                  @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                  @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                  @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                  @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservKhordad.php")
    Single<Status> sendReservInformationKhordad(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                    @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                    @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                    @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                    @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                    @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                    @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservTir.php")
    Single<Status> sendReservInformationTir(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservMordad.php")
    Single<Status> sendReservInformationMordad(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservShahrivar.php")
    Single<Status> sendReservInformationShahrivar(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservMehr.php")
    Single<Status> sendReservInformationMehr(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservAban.php")
    Single<Status> sendReservInformationAban(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservAzar.php")
    Single<Status> sendReservInformationAzar(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservDey.php")
    Single<Status> sendReservInformationDey(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservBahman.php")
    Single<Status> sendReservInformationBahman(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);



    @FormUrlEncoded
    @POST("SaveReservEsfand.php")
    Single<Status> sendReservInformationEsfand(@Field("fullName") String fullName, @Field("phone") String phone, @Field("customerId") String customerId,
                                                @Field("nafaratAvaliyeh") int nafaratAvaliyeh, @Field("nafaratMazad") int nafaratMazad, @Field("finalCoast") int finalCoast,
                                                @Field("coastReceived") int coastReceived, @Field("numberFish") String numberFish, @Field("reservistPerson") String reservistPerson,
                                                @Field("resSuite1") String resSuite1, @Field("resSuite2") String resSuite2, @Field("resSuite3") String resSuite3,
                                                @Field("resSuite4") String resSuite4, @Field("resSuite5") String resSuite5, @Field("resSuite6") String resSuite6,
                                                @Field("resSuite7") String resSuite7, @Field("resSuite8") String resSuite8, @Field("resSuite9") String resSuite9,
                                                @Field("resSuite10") String resSuite10, @Field("resSuite11") String resSuite11, @Field("resSuite12") String resSuite12);












    final class ApiClient {
        public final String BASE_URL = "https://parcactivity.ir/rezaGuestSite/";
        public static final ApiClient CLIENT;

        static {
            CLIENT = new ApiClient();
        }


        public final ApiService getClient() {
            Object object = (new Retrofit.Builder())
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService.class);

            return (ApiService) object;

        }

    }


}
