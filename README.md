# Match Mate - Matrimonial App Simulation for Shaadi.com assignment


### Setup Instructions
  - Clone repository
  - Open it in Android Studio
  - Run on Device

### API and Added Fields Description
- Data fetched from [RandomUser API](https://randomuser.me/api/?results=10)
- Added 2 fields: Family type ("Joint", "Nuclear") and Diet Options("Veg", "Jain", "Non-Veg", "Vegan")
- I chose these fields because the type of food we eat dictates our lifestyle, and also, many people have a preference for privacy, which is very highly rated

 ### Libraries
- **Retrofit** – Networking & API calls, it simplifies network calls and uses boilerplate code
- **Glide** – Image loading & caching, it is smooth and fast, as it has resource pooling
- **Room** – Local database. Same as retrofit can be used as an interface and simplifies DB calls
- **Kotlin Coroutines + Flow** – Asynchronous data handling  
- **Material Components (M3)** – Modern UI

### Architecture
- **MVVM** (Model–View–ViewModel) used:
  - ViewModel survives configuration changes.
  - Works seamlessly with Room + Coroutines/Flow.
### **[RemoteMediator](https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator)** used for pagination offline Handling, and Syncing
  - It is from the Android Paging 3 library
  - It is built only for offline-first apps with large datasets
  - It is like a middle layer between the remote and local data source
  - Offline first app because the single source of truth is the room DB, and we sync data from the server
  - It also handles getting paged data from remote, hence the offline condition is also handled
  - It has load states that can be collected in UI to handle errors and show no item cases

### Flaky network testing
  - Used [Flaker](https://github.com/RotBolt/Flaker)
  - We directly get an intuitive tool to simulate flaky and validate the app's reliability
  - Just added in http client of the Retrofit object
    ``` kotlin
         fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                FlakerInterceptor
                    .Builder()
                    .build()
            )
            .build()
    }
    ```
 
### Match score logic description
  - The higher the score better the match
  - We get the age difference and calculate something like a penalty; the lesser the age gap, the less the penalty
  - Same penalty for city, diet, and family, but here 0 is a perfect match and 1 is a mismatch
  - I multiply them by the weight and divide by the weight sum to average them
  - Then at last they are inverted so that higher values represent stronger matches.

     ```kotlin
              // likely values are closer to zero
        val score =
            100 * (cityMatchScore * CITY_WEIGHT + dietMatchScore * DIET_WEIGHT + familyMatchScore * FAMILY_WEIGHT + ageScore * AGE_WEIGHT) / WEIGHT_SUM

        return MAX_SCORE - score.toInt()

     
        const val AGE_WEIGHT = 100
        const val CITY_WEIGHT = 20
        const val DIET_WEIGHT = 50
        const val FAMILY_WEIGHT = 20
        const val MAX_SCORE = 100
        const val MIN_SCORE = 0
        const val WEIGHT_SUM = AGE_WEIGHT + CITY_WEIGHT + DIET_WEIGHT + FAMILY_WEIGHT
     ```

 ### Design Constraint Handling
   - If we cannot show the user profile photo, then we can show either default images or like image with an abbreviation like phone contacts APP
 ### Reflection Task
   - I would add a screen to get my user data from the current user
   - Also, I would make the match score algorithm dynamic, add a filter screen with toggles for each field, and the user will turn on or off the particular field, and then they would get the match score accordingly
  
