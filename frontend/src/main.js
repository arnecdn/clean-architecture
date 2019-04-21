// main.js
import Vue from 'vue';
import VueRouter from 'vue-router';

import Selger from './components/ansatt/Selger.vue';
import Kunde from './components/kunde/Kunde.vue';
import Produkt from './components/produkt/Produkt.vue';
import Salg from './components/salg/Salg.vue';
import SalgGraphql from './components/salg/SalgGraphql.vue';

import {ApolloClient} from 'apollo-client'
import {HttpLink} from 'apollo-link-http'
import {InMemoryCache} from 'apollo-cache-inmemory'
import VueApollo from 'vue-apollo'


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
    }
  ]
});

new Vue({
  provide: apolloProvider.provide(),
  router,
  template: `
    <div>
      <nav>
        <div >
          <ul class="navbar-nav">            
            <router-link to="/selger" class="nav-link">Selger</router-link>
            <router-link to="/kunde" class="nav-link">Kunde</router-link>
            <router-link to="/produkt" class="nav-link">Produkt</router-link>
            <router-link to="/salg" class="nav-link">Salg</router-link>
            <router-link to="/salg_graphql" class="nav-link">SalgGraphql</router-link>
          </ul>
        </div>
      </nav>
      <router-view class="view"></router-view>
    </div>
  `
}).$mount('#app');