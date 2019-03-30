<template>
  <div id="list-selgere">
    <ul>
      <li v-for="selger in selgere">
        <router-link :to="{name:'selger_endre', params:{ id: selger.id }}">{{selger.navn}}</router-link>
      </li>
    </ul>
  </div>
</template>
<script>
  import axios from 'axios'

  export default {
    data() {
      return {
        selgere: [],
        selgerTilLagring: {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      hentAlle() {
        axios.get('/ansatt', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgere = response.data))

      },


    },
    mounted() {
      this.hentAlle()
    },
    computed: {
      oppdater:function() {
        this.hentAlle();
      }
    }
  }
</script>
