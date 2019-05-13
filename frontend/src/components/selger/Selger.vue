<template>
  <div>
    <div class="w3-container" id="menu">
      <div class="w3-content" style="max-width:700px">

        <h5 class="w3-center w3-padding-48"><span class="w3-tag w3-wide">Selger administrasjon</span></h5>
        <div class="w3-container w3-padding-48 w3-card">
          <input v-model="selgerTilLagring.navn" placeholder="Selgernavn">
          <button v-if="selgerTilLagring.id == ''" v-on:click.stop.prevent="opprettSelger">Opprett selger</button>
          <button v-if="selgerTilLagring.id != ''" v-on:click.stop.prevent="lagreSelger(selgerTilLagring.id)">Lagre selger</button>
          <button v-if="selgerTilLagring.id != ''" v-on:click.stop.prevent="slettSelger(selgerTilLagring.id)">Slett</button>

          <button v-if="selgerTilLagring.id != ''" v-on:click.stop.prevent="tømFelter()">Tøm felter</button>
          <p>{{ melding }}</p>
        </div>
        <div class="w3-container w3-padding-48 w3-card">
          <div v-for="selger in selgere">
            <a class="w3-button w3-block w3-white" v-on:click="hentAnsatt(selger.id)">

              <div>
                <h5>{{selger.navn}}</h5>
              </div>
            </a>
          </div>
        </div>

        <!--img src="/w3images/coffeehouse2.jpg" style="width:100%;max-width:1000px;margin-top:32px;"-->
      </div>
    </div>
  </div>

</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        selgere: [],
        selgerTilLagring: {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      tømFelter() {
        this.selgerTilLagring.id = ''
        this.selgerTilLagring.navn = ''
      },
      lagreSelger(id) {
        axios
          .put('/api/selger/' + id,
            this.selgerTilLagring
            ,
            {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Selger er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettSelger() {

        axios
          .put('/api/selger/',
            this.selgerTilLagring
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Selger er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettSelger(id) {
        axios.delete('/api/selger/' + id, {},
          {}
        ).then(response => {
          this.selgere = response.data
          this.hentAlle()
        })
      },
      hentAnsatt(id) {
        axios.get('/api/selger/' + id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgerTilLagring = response.data))
      },
      hentAlle() {
        axios.get('/api/selger', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgere = response.data))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

      },
    },

    mounted() {
      console.log('hent alle')
      this.hentAlle()
    }
  }
</script>