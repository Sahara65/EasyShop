# EasyShop

## Overview
The EasyShop store is an online e-commerce website where users can browse for any potential items they wish to purchase, create an account, log in, and add products to the cart. 

This project revolves around the completion of several classes, bug fixing, postman testing, and the database. There are 5 phases for the overall completion of this project, and at this moment only Phases 1-3 have been fully completed. I plan on completing phases 4-5 within the near future.

## The EasyShop Website!
- **The website's layout:**
  
![EasyShopWebsite(Final)](https://github.com/Sahara65/EasyShop/assets/93899072/114fffe0-40fa-4b1d-ac3a-dbede29a2a8b)

  
- **Logging in:**
  
![EasyShopWebsite(LoggingIn)](https://github.com/Sahara65/EasyShop/assets/93899072/2d2c6ca7-3828-4810-8556-21f2204a0b9a)


- **Adding a product to the cart:**
  
![ezgif com-optimize (2)](https://github.com/Sahara65/EasyShop/assets/93899072/e0e2225f-8cd4-4150-abec-194b722af6f1)


- **Clearing the Cart:**
  
![EasyShopWebsite(ClearCart)](https://github.com/Sahara65/EasyShop/assets/93899072/b3e3a8dc-0410-416f-938f-60ea2384b953)


 
## The Database
- This is where the magic happens. The backbone of this project has been the database! Below is the diagram of the database's structure and all the tables that were integral to the success of this project:

- **Diagram:**
  
![Screenshot 2024-01-05 085906](https://github.com/Sahara65/EasyShop/assets/93899072/2ee3b083-aad8-4b99-97ca-03f6d2e3b839)

- **Tables:**
  
![Screenshot_31](https://github.com/Sahara65/EasyShop/assets/93899072/d4598c0e-d75c-4a22-9e9e-58d66e495b53)



## Postman Testing
- Postman was crucial to the success of this project as it aided in checking if the code worked and pinpointing where any issues may be. The following tests were conducted:

- **Phases 1-2 testing:**

![EasyShopWebsitePostman1](https://github.com/Sahara65/EasyShop/assets/93899072/da83d2fc-476b-4393-88f0-562b0b194eaf)

- **Phase 3 testing:**
  
![PostmanTests2](https://github.com/Sahara65/EasyShop/assets/93899072/b61b2f3b-a4bf-4704-a82d-f2a2d624825c)
- Due to Phases 4-5 being actively worked on, at the moment of this ReadMe being posted, only the User Profiles and Checkout integration tests are failing.
  


## Phase 1 - The Categories
- During Phase 1, the primary focus was implementing methods for the CategoriesController and MySqlCategoriesDao. By changing annotations and completing logic for the following methods, Phase 1 was completed successfully.

- **CategoriesController:**
![EasyShopCodeCategoriesController](https://github.com/Sahara65/EasyShop/assets/93899072/7396c4a7-1e3f-4fa6-8926-cadc4955a18e)

- **MySqlCategoriesDao:**
![ezgif com-optimize (4)](https://github.com/Sahara65/EasyShop/assets/93899072/2db7b108-03b6-4889-a9e8-ed040c9b417a)


## Phase 2 - Fixing Bugs
- During Phase 2, there were two bugs noticed by our users.
  
- **Bug Fix #1:** The first bug that was reported was the search function returning incorrect results. I've managed to locate the bug within these snippets of code and noticed that some of the logic was missing. The maxPrice was missing in both sections of the code. I've placed arrows on the code that I have added:

![Screenshot_29](https://github.com/Sahara65/EasyShop/assets/93899072/83212b01-1303-41e2-bb6e-e5447e722614)
  
- **Bug Fix #2:** The second bug users reported was that the products seemed to be duplicated. One particular example was where a laptop was listed 3 times despite being the same product. So I looked into the code to see what the issue may be and noticed in this method that it originally was set to return productDao.create(product) inside of the updateProduct method instead of updating, thus causing the bug. I've updated it as follows:

![Screenshot_30](https://github.com/Sahara65/EasyShop/assets/93899072/c169b12a-471b-4d4f-b753-42c2fb5983f4)



## Phase 3 - The Shopping Cart
- During Phase 3, the goal is to create/complete the shopping cart functionality so that the user can add and remove items from their cart. I've created 

- **ShoppingCartController:**
![ShoppingCartController](https://github.com/Sahara65/EasyShop/assets/93899072/23790d49-f27b-46fd-9492-1cccd8333757)

  
- **ShoppingCartDao:**
![Screenshot_33](https://github.com/Sahara65/EasyShop/assets/93899072/759d40ee-a5c3-47d1-b1ce-d5bfe5258ee0)

  
- **MySqlShoppingCartDao:**
![ezgif com-optimize (3)](https://github.com/Sahara65/EasyShop/assets/93899072/56607e0a-e11e-4aa9-bc0b-15d6417c0663)



## Phase 4 - The User Profile
- Phase 4 involves creating the functionality of registering a new user's account and the creation of their profile.
- As of this moment, phase 4 has not been implemented.

## Phase 5 - The Checkout
- Phase 5 involves the proper function of checking out the user's shopping cart so that their order will be placed. 
- As of this moment, phase 5 has not been completed. 


## Interesting Piece of Code!
My interesting piece of code is the following:
![Screenshot_32](https://github.com/Sahara65/EasyShop/assets/93899072/69d81ed2-d0ab-41b8-8493-e2e35a2cdf56)


- The reason why I find this code interesting is due to the amount of issues I encountered with my integration tests. Previously, I had the initialization of my object placed outside of my while loop. This caused a whole slew of issues such as prior data being kept alive long after the loop has run and still retaining this data after we try to add a product again. 
- I managed to fix this by initializing the shoppingCartItem inside of the while loop, so that once the loop runs, the data will be properly stored and will clear the object of its prior data.
Once I did this, all my bugs went away when I ran my integration tests.
