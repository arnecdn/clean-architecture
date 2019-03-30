<template>
  <div id="opprett-selger">
    <input v-model="selgerTilLagring.navn" placeholder="Selgernavn">
    <button v-on:click="lagreSelger" v-text="knappTekst"></button>
    <p>{{ melding }}</p>
    <li v-for="selger in selgere">
      <router-link :to="{name:'selger_endre', params:{ id: selger.id }}">{{selger.navn}}</router-link>
    </li>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        knappTekst: 'Opprett selgerTilLagring',
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
      lagreSelger() {
        if (this.selgerTilLagring.navn.length == 0) {
          return;
        }
        var selgerId = ''
        if (this.$route.params.id != undefined) {
          selgerId = this.$route.params.id
        }

        axios
          .put('/ansatt/' + selgerId, {
              navn: this.selgerTilLagring.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => (
            this.melding = 'Selger er oppdatert!'
          ))
          .catch(error => {
            this.melding ="Feil. Sjekk console"
            console.log(error.response)
          });

        this.melding = 'Selger ' + this.selgernavn + ' ble opprettet'
        this.selgerTilLagring.navn = ''
        this.selgerTilLagring.id = ''
        console.log(this.melding)
        this.$emit ('lagre', 7);
      }
    },
    mounted() {
      this.hentAlle()
    },
    watch: {
      '$route.params.id'(newId, oldId) {
        this.melding =''
        if (this.$route.params.id == undefined) {
          return
        }
        this.knappTekst = 'Lagre selgerTilLagring'

        var selgerId = this.$route.params.id

        axios.get('/ansatt/' + selgerId, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgerTilLagring = response.data))

      }

    }
  }
</script>