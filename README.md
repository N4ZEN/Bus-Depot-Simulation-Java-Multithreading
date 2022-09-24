<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>

# FinalBusDepot

A concurrent programming simulation built in Java in order to determine the optimal number of ramps to install in a Bus Depot, which is cost effective and minimizes the bus wating times. Currently, the bus depot is losing customers as some buses have long waiting times. 

## Getting Started
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#assumptions">Assumptions</a></li>
        <li><a href="#default-settings">Default settings</a></li>
        <li><a href="#executing-program">Executing program</a></li>
      </ul>
    </li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project

In this bus depot, the Buses have to enter and exit from the same ramp which can only pass one bus at a time. Each bus takes 30 seconds to cross the ramp. 
The aim is to determine the maximum waiting times of each bus and the number of buses that can pass during a day, so that we can deduce whether there is a need for an additional ramp to be built. 
Each bus waits to enter until the ramp is free and then goes inside and waits again until they can proceed to either the Cleaning Bay or Mechanics Bay depending on the service that the bus needs. At that point cleaners/mechanics will service the buses and then they will proceed back to the waiting area until the ramp opens and it can exit. 
Since these processes occur simultaneously, and some of these entities happen to be sharing one or more resources at the same time, we utilized concurrent concepts which would allow threads of each entity, such as Bus, Cleaners and Mechanics to be created and intelligently link the resources among them, so that each entity gets a fair share of the resources. Concepts such as semaphore, synchronization, locks, and countdown latches are utilized to simulate and mimic this behaviour. 


### Built With
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)


### Assumptions
Just like the real world, the threads are generated at random time intervals.
Once a Bus starts enters the Bus Depot, it does not leave until it finished the service it came for.

### Default settings
Application does not rely on user inputs. 
Each thread has a predefined time for sleeping based on the activities happening. 
Time stamp is used throughout the Bus Depot class, where each class uses the timestamp for each process that occurs. 


### Executing program
Application only has a console UI (has no graphical interface) so can be run on any terminal. 

<!-- LICENSE -->
## License

Not Licensed. 


<!-- CONTACT -->
## Contact

N4ZEN- naza.zuhair@gmail.com

Project Link: https://github.com/N4ZEN/Simulation-Java-Multithreading

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->

[Next-url]: https://nextjs.org/
[ReactNative.js]: https://img.shields.io/badge/ReactNative-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[ReactNative-url]: https://reactnative.dev/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 




 
