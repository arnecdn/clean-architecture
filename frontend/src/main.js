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
    {path: '/selger', component: Selger,
    name : 'selger'},
    {
      path: '/selger/:id', component: Selger,
      name: 'selger_endre'
    },
    { path: '/kunde', component: Kunde },
    { path: '/produkt', component: Produkt },
    { path: '/salg', component: Salg }
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