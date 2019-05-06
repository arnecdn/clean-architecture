// main.js
import Vue from 'vue';
import VueRouter from 'vue-router';

import Selger from './components/selger/Selger.vue';
import Kunde from './components/kunde/Kunde.vue';
import Produkt from './components/produkt/Produkt.vue';
import Salg from './components/salg/Salg.vue';
import SalgGraphql from './components/salg/SalgGraphql.vue';
import SalgGraphqlFormWizard from './components/salg/SalgGraphqlFormWizard.vue';

import { ApolloClient } from 'apollo-client'
import { HttpLink } from 'apollo-link-http'
import { InMemoryCache } from 'apollo-cache-inmemory'
import VueApollo from 'vue-apollo'

//global registration
import VueFormWizard from 'vue-form-wizard'
import 'vue-form-wizard/dist/vue-form-wizard.min.css'
Vue.use(VueFormWizard)

const httpLink = new HttpLink({
  // You should use an absolute URL here
  uri: '/api/graphql'
})

const apolloClient = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache(),
  connectToDevTools: true
})

Vue.use(VueApollo)
Vue.use(VueRouter);

const apolloProvider = new VueApollo({
  defaultClient: apolloClient,
  defaultOptions: {
    $loadingKey: 'loading'
  }
})

const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    {
      path: '/selger',
      component: Selger,
      name: 'selger'
    },
    {
      path: '/kunde',
      component: Kunde,
      name: 'kunde'
    },
    {
      path: '/produkt',
      component: Produkt,
      name: 'produkt'
    },
    {
      path: '/salg',
      component: Salg,
      name: 'salg'
    },
    {
      path: '/salg_graphql',
      component: SalgGraphql,
      name: 'salg_graphql'
    },
    {
      path: '/salg_graphql_form_wizard',
      component: SalgGraphqlFormWizard,
      name: 'salg_graphql_form_wizard'
    }
  ]
});

new Vue({
  provide: apolloProvider.provide(),
  router,
  template: `
<div>
<div class="w3-top">
  <div class="w3-row w3-padding w3-black">
  <div class="w3-col s3">
            <router-link to="/selger" class="w3-button w3-block w3-black"" >Selger</router-link>
  </div>
  <div class="w3-col s3">
            <router-link to="/kunde" class="w3-button w3-block w3-black"">Kunde</router-link>
  </div>
  <div class="w3-col s3">
            <router-link to="/produkt" class="w3-button w3-block w3-black"">Produkt</router-link>
  </div>  
  <div class="w3-col s3">
            <router-link to="/salg_graphql" class="w3-button w3-block w3-black"">Salg</router-link>
  </div>
<div class="w3-col s3">
            <router-link to="/salg_graphql_form_wizard" class="w3-button w3-block w3-black"">Salg</router-link>
  </div>
  </div>
      <router-view class="view"></router-view>

  </div>
<!-- Header with image -->
<header class="bgimg w3-display-container w3-grayscale-min" id="home">
  <div class="w3-display-bottomleft w3-center w3-padding-large w3-hide-small">
    <span class="w3-tag">Open from 6am to 5pm</span>
  </div>
  <div class="w3-display-middle w3-center">
    <span class="w3-text-white" style="font-size:90px">Butikken</span>
  </div>
  <div class="w3-display-bottomright w3-center w3-padding-large">
    <span class="w3-text-white">15 Adr street, 5015</span>
  </div>
</header>

<!-- Footer -->
<footer class="w3-center w3-light-grey w3-padding-48 w3-large">
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-text-green">w3.css</a></p>
</footer>
</div>

  `
}).$mount('#app');

