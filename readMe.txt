1. Project Overview

    * Getting greeting from local time
    * Did not get field 'name' in api response so showing 'buddy' in place of name field.
    * Created chart on basis of past 30 days data
    * Switch data between top link and recent links

2. Dependencies - MpCharts : Charts
                  retrofit : api calling

3. Architecture and Design

    Home_activity : follows MVVM architecture and Data binding
    * HomeActivity <UI> - Responsible for managing UI, laying out graph, setting data to variable in xml
                          and observe changes from view-model
    * HomeViewModel <ViewModel> - It is responsible for fetching data and maintaining data in life conscious ways.
                                  making sure in future orientation changes and separating responsibility of getting data
    * <Model> ->
        data class LinkData - fields (
        , yAxis, xTicks) are need to be populated by developer for graph purposes
                    |
                    |-- @field
                         data: <LinkDetails>
                                    |
                                    |-- Link


    Retrofit: follows Singleton
    * RetrofitClient - Create Retrofit instance and get retrofit function to call api
    * RetrofitInterface - Define behaviour and functionality

4. Custom Components
    * CustomButtons -> extends Constraint layout which inflates layout from `button_border_transparent.xml`
                       this button has grey border , with icon and text in center
                       xml tag - `com.example.openit.ui.CustomButton`

5. Api and its schema
    base url - `https://api.inopenapp.com/`

   dashboard data -> `api/v1/dashboardNew`
                     Bearer token
                     Schema - data class LinkData

6. Extension
        String
            |
            | .militaryToDD_MMM_YYY() -> convert this format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' to DD_MM_YYYY

7. utils
        |
        | getGreetings() -> give text like good morning according to time
        |
        | makeDateSetLineGraph() -> arguments in ArrayList<Int> return data set with graph color and design suitable for mpcharts
        |
        | getXYaxis() -> get xAxis , yAxis , xTicks value from : data class LinKData -> overall_url_chart list
