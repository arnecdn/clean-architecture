// main.js
import Vue from 'vue';
import VueRouter from 'vue-router';

import Selger from './components/ansatt/Selger.vue';
import Kunde from './components/kunde/Kunde.vue';
import Produkt from './components/produkt/Produkt.vue';
import Salg from './components/salg/Salg.vue';

Vue.use(VueRouter);

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
      path: '/selger/:id',
      component: Selger,
      name: 'selger_lagre'
    },
    {
      path: '/kunde',
      component: Kunde,
      name: 'kunde'
    },
    {
      path: '/kunde/:id',
      component: Kunde,
      name: 'kunde_lagre'
    },
    {
      path: '/produkt',
      component: Produkt,
      name: 'produkt'
    },
    {
      path: '/produkt/:id',
      component: Produkt,
      name: 'produkt_lagre'
    },
    {
      path: '/salg',
      component: Salg,
      name: 'salg'
    }, {
      path: '/salg/:id',
      component: Salg,
      name: 'salg_lagre'
    }
  ]
});

new Vue({
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
          </ul>
        </div>
      </nav>
      <router-view class="view"></router-view>
    </div>
  `
}).$mount('#app');