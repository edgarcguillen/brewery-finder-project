import Vue from "vue";
import Router from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Logout from "../views/Logout.vue";
import Register from "../views/Register.vue";
import store from "../store/index";
import LandingPage from "../views/LandingPage.vue";
import BreweryPage from "../views/BreweryPage.vue";
import BrewerDashboard from "../views/BrewerDashboard.vue";
import AddBrewery from "../views/AddBrewery.vue";
import AdminPage from "../views/AdminPage.vue";
import UserDashboard from "../views/UserDashboard.vue";

import BeerReviewPage from "../components/reviews/BeerReviewPage.vue";
import MyBrewery from "../views/MyBrewery.vue";
import EditBrewery from "../views/EditBrewery.vue";
import AddBeer from "../views/AddBeer.vue";

Vue.use(Router);

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/welcome",
      name: "welcome",
      component: LandingPage,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/brewery/:id",
      name: "BreweryPage",
      component: BreweryPage,
      meta: {
        requiresAuth: false,
      },
    },
    //Beer review page
    {
      path: "/brewery/:id/:beerID",
      name: "BeerReviewPage",
      component: BeerReviewPage,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/dashboard/:brewerID",
      name: "BrewerDashboard",
      component: BrewerDashboard,
      meta: {
        requiresAuth: true, // will eventually be true
      },
    },
    {
      path: "/dashboard/:brewerID/brewery/create",
      name: "AddBrewery",
      component: AddBrewery,
      meta: {
        requiresAuth: true, // will eventually be true
      },
    },
    {
      path: "/dashboard/:brewerID/brewery/:breweryID/",
      name: "MyBrewery",
      component: MyBrewery,
      meta: {
        requiresAuth: true, // will eventually be true
      },
    },

    {
      path: "/dashboard/:brewerID/brewery/:breweryID/edit",
      name: "EditBrewery",
      component: EditBrewery,
      meta: {
        requiresAuth: true, // will eventually be true
      },
    },
    {
      path: "/dashboard/:brewerID/brewery/:breweryID/beer/create",
      name: "AddBeer",
      component: AddBeer,
      meta: {
        requiresAuth: true, // will eventually be true
      },
    },
    {
      path: "/admin",
      name: "admin",
      component: AdminPage,
      meta: {
        requiresAuth: true,
      },
    },
    {
      //TODO
      //changed to test and design without being logged in. need to eventually uncomment
      // path: "/dashboard/user/:userID",
      path: "/user",
      name: "UserDashboard",
      component: UserDashboard,
      meta: {
        requiresAuth: true,
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === "") {
    next("/welcome");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
