<template>
  <div id="list-selgere">
    <ul>
      <li v-for="selger in selgere">
        <router-link :to="{name:'selger_endre', params:{ id: selger.id }}" >{{selger.navn}}</router-link>
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
        selger: {
          id: '',
          navn: ''
        }
      }
    },
    mounted() {
      axios.get('/ansatt', {},
        {
          headers: {
            'Content-type': 'application/json',
          }
        }
      ).then(response => (this.selgere = response.data))

    },
    methods: {
      opprett() {
        return ""
      }
    }
  }
</script>