# Trivago Challenge
## Techonologies

- Kotlin
- Retrofit
- Koin
- Dynamic Features
- Flow Coroutines
- Moshi
- Jetpack libraries

## Methodologies

- TDD
- Clean Archictecture
- MVVM
- DDD

## To Improve In Future

- As the Ui tests on dynamic modules can generate a lot of problems I decided not to apply them, but I know how importat it's
- Unit test all error cases
- Use database to save as the user's last searches
- (Personal Opinion) -> I propably would create other two modules to separe concerns inside data(remote and local)

# Day to Day

## Saturday
Because of my current job I started the project here, so I spent these day to think about architecture, technologies and code strategy.

## Sunday
I began to code using TDD strategy to get a good unit test coverage in the end of the day I already had 50% of search feature tested and done.


## Monday
I began to code after job, so I put all my focus in finish search feature and these tests at this moment I had some fear to not ship the project in the right time.

## Tuesday
I began to code after job, so at this moment I already had finished the search feature using mocks and started detail feature was complicated because Swapi Api works with hypermedia queries so some parts of feature was necessary use advanced resources but good point I had so much fun. I finished the day with 70-75% of detail feature using mocks.

## Wednesday
I began to code and substitute mocks per production code was so easy only needed change the datasource, I put my focus in Ui improvemets, but again I need to put some attention in my current job. So I couldn't develop cache implementation, It frustrated me so much because I have total knowledge that search feature is an awesome task to use cache, but as say before I would like to implement this in future.

## Complete Explain of Methodologies

To share knowledge the links below has the most of part the concepts I had used to implement the project

| Methodology | Link |
| ------ | ------ |
| Clean Architecture | [By Uncle Bob][PlDb] |
| MVVM | [By Android Team][PlGh] |
| TDD | [By Uncle Bob][PlGd] |
| DDD | [By Martin Fowler][PlOd] |
| PresentationDomainDataLayering | [By Martin Fowler][PlOe] |

# Final Considerations
Guys first of all thank you so much to invite me to the test, was pretty good to show my knowledge and helped me to find new solutions about hypermedia problems(Detail Screen). I hope to see you guys to discuss about other possibles resolutions. I want to say sorry because I shipped the code at last day but here at Globo we're very very busy with a product who has more than 200 millions access per week, so everything is a big problem, but I gave my best. Thanks again.


   [PlDb]: <https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html>
   [PlGh]: <https://developer.android.com/jetpack/guide?hl=pt-br>
   [PlGd]: <https://www.youtube.com/watch?v=qkblc5WRn-U>
   [PlOd]: <https://martinfowler.com/bliki/DomainDrivenDesign.html>
   [PlOe]: <https://martinfowler.com/bliki/PresentationDomainDataLayering.html>

