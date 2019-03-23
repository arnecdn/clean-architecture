// main.js
import Vue from 'vue';
import VueRouter from 'vue-router';

import Ansatt from './components/ansatt/Selger.vue';
import Kunde from './components/kunde/Kunde.vue';
import Produkt from './components/produkt/Produkt.vue';
import Salg from './components/salg/Salg.vue';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    { path: '/ansatt', component: Ansatt },
    { path: '/kunde', component: Kunde },
    { path: '/produkt', component: Produkt },
    { path: '/salg', component: Salg}
  ]
});

new Vue({router,
  template: `
    <div>
      <nav>
        <div >
          <ul class="navbar-nav">            
            <li class="nav-item"><router-link to="/ansatt" class="nav-link">Ansatt</router-link></li>
            <li class="nav-item"><router-link to="/kunde" class="nav-link">Kunde</router-link></li>
            <li class="nav-item"><router-link to="/produkt" class="nav-link">Produkt</router-link></li>
            <li class="nav-item"><router-link to="/salg" class="nav-link">Salg</router-link></li>
          </ul>
        </div>
      </nav>
      <router-view class="view"></router-view>
    </div>
  `
}).$mount('#app');