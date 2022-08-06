<div id="top"></div>

<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h2 align="center">My choice pizza</h2>

</div>


<!-- ABOUT THE PROJECT -->
### About The Project

My choice pizza is an application that enables users quickly and easily to order a pizza according to their taste. The users choose the size, the sauce and the ingredients for their pizza. Every pizza is unique. 
The order's details can be confirmed by the user in his shopping cart. The administrator is responsible for all waiting orders and can change their status, when they are ready. The administrator can also edit the prices and the ingredients, and can manage the user's roles (Admin/User).

### Built With

* [Spring Framework](https://spring.io/)
* [Thymeleaf](https://www.thymeleaf.org/)
* [Bootstrap](https://getbootstrap.com/)
* [JUnit5](https://junit.org/junit5//)
* [Mockito](https://site.mockito.org/)
* [MySQL](https://www.mysql.com/)
* [HSQLDB](https://hsqldb.org/)
* [Maven](https://maven.apache.org/guides/index.html)
* [Cloudinary](https://cloudinary.com/)


<!-- GETTING STARTED -->

### Installation

1. Get a free API Key at [https://github.com/IvAngelova/My-Choice-Pizza-Project-Spring](https://github.com/IvAngelova/My-Choice-Pizza-Project-Spring)
2. Clone the repo
   ```sh
   git clone https://github.com/IvAngelova/My-Choice-Pizza-Project-Spring.git
   ```
3. Setup MySQL in `application.yml`
   ```yml
   datasource:
        password: YOUR_DS_PASSWORD
        username: YOUR_DS_USERNAME
   ```
4. Setup Cloudinary Properties in `application.yml`
   ```yml
    cloudinary:
     api-key: YOUR_API_KEY
     api-secret: YOUR_SECRET_KEY
     cloud-Name: YOUR_CLOUD_NAME
   ```
5. Init data only once `application.yml`
    ```yml
     sql:
      init:
         mode: always
   ```

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png